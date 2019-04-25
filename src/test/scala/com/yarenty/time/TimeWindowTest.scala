// Copyright (C) 2018-2019 yarenty.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.yarenty.time


import java.util.Date

import org.junit.Test

class TimeWindowTest {

	val start = new Date()
	val end = new Date(start.getTime + 10000)

	@Test(expected = classOf[AssertionError])
	def nullNotAcceptedImplicit(): Unit = {
		import TimeWindowImplicits._
		val tw: TimeWindow = (null, null)
	}

	@Test //(expected = classOf[AssertionError])
	def nullNotAccepted(): Unit = {
		val tw: TimeWindow = TimeWindow(null, null)
		assert(tw.isInstanceOf[InfiniteTimeWindow])
		println(tw)
	}

	@Test
	def nullStartNotAccepted(): Unit = {
		val tw: TimeWindow = TimeWindow(start, null)
		assert(tw.isInstanceOf[PlusInfTimeWindow])
		println(tw)
	}

	@Test
	def nullEndNotAccepted(): Unit = {
		val tw: TimeWindow = TimeWindow(null, end)
		assert(tw.isInstanceOf[MinusInfTimeWindow])
		println(tw)
	}

	@Test
	def startEndOK(): Unit = {
		val tw: TimeWindow = TimeWindow(start, end)
		assert(tw.isInstanceOf[FiniteTimeWindow])
		println(tw)
	}


	@Test
	def startEndTheSame(): Unit = {
		val tw: TimeWindow = TimeWindow(start, start)
		assert(tw.isInstanceOf[FiniteTimeWindow])
		println(tw)
	}

	@Test
	def startBeforeEndBad(): Unit = {
		val tw: TimeWindow = TimeWindow(end, start)
		assert(tw.isInstanceOf[FiniteTimeWindow])
		println(tw)
	}


}
