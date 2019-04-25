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

package com.yarenty.time

import java.util.Date

final class PlusInfTimeWindow(val start:Date) extends InfiniteTimeWindow {
	override def toString = s"[$start ==> ***]"
	def compare(other: TimeWindow) = other match {
		case x if x eq TimeWindow.Infinite  => -1 // Undefined != Undefined
		case x if x eq this      => 0  // `case Inf` will include null checks in the byte code
		case _                   => 1
	}
	override def unary_- : TimeWindow = TimeWindow.MinusInf(start)

	override def +(other: TimeWindow): TimeWindow = other match {
		case x if x eq TimeWindow.Infinite       => TimeWindow.Infinite
		case x: InfiniteTimeWindow if x ne this => TimeWindow.Infinite
		case _                        => this
	}
	override def -(other: TimeWindow): TimeWindow = other match {
		case x if x eq TimeWindow.Infinite       => TimeWindow.Infinite
		case x: InfiniteTimeWindow if x eq this => TimeWindow.Infinite
		case _                        => this
	}

	override def duration(): Long = Long.MaxValue
}