// Copyright (C) 2018-2019 yarenty.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at

// http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.yarenty

import java.util.Date

package object time {


	object TimeWindowImplicits {

		//	def apply: Any = ???
		implicit def tuple2TimeTindow(t: Tuple2[Date, Date]): TimeWindow = {
			assert(t._1 != null && t._2 != null, "Both dates are null - it doesn't make sense")
			TimeWindow(min(t._1, t._2), max(t._1, t._2))
		}

		def min(t1: Date, t2: Date): Date = {
			if (t1.before(t2)) t1 else t2
		}

		def max(t1: Date, t2: Date): Date = {
			if (t1.before(t2)) t2 else t1
		}

	}
}
