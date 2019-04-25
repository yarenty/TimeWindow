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


object TimeWindow {
	
	val Zero = new ZeroTimeWindow()

	val Infinite: InfiniteTimeWindow = new InfiniteTimeWindow()

	def apply(start: Date, end: Date): TimeWindow = (start, end) match {
		case (s: Date, e: Date) => if (e.before(s)) new FiniteTimeWindow(end, start) else new FiniteTimeWindow(start, end)
		case (s: Date, null) => PlusInf(start)
		case (null, e: Date) => MinusInf(end)
		case (null, null) => Infinite
	}

	def PlusInf(start: Date): PlusInfTimeWindow = new PlusInfTimeWindow(start)

	def MinusInf(end: Date): MinusInfTimeWindow = new MinusInfTimeWindow(end)

}


/**
	* HOw to deal with NULL
	* (start,end) - all works
	* (null,end) + ( start,null) = (null,null)? 
	* (null,end) |+| ( start,null) = (start end) or (null,null) 
	*
	* @param start
	* @param end
	*/
//sealed 
abstract class TimeWindow { //(var start: Date, var end: Date) {

	//	println(this)

	def +(other: TimeWindow): TimeWindow

	def plus(other: TimeWindow): TimeWindow = this + other

	def -(other: TimeWindow): TimeWindow

	def minus(other: TimeWindow):TimeWindow = this - other

	def isFinite(): Boolean

	def duration(): Long

	//	def max(other: TimeWindow): TimeWindow = if (this > other) this else other
	//	def gt(other: TimeWindow)    = this > other
	//	def gteq(other: TimeWindow)  = this >= other
	//	def lt(other: TimeWindow)    = this < other
	//	def lteq(other: TimeWindow)  = this <= other

	def unary_- : TimeWindow

}

