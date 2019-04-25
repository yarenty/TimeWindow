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

sealed class FiniteTimeWindow(val start:Date, val end:Date) extends TimeWindow {

	override def toString: String = s"[$start <=> $end]"

	import TimeWindowImplicits._
	

	final def isFinite() = true

	def +(other: TimeWindow):TimeWindow = other match {
		case x: FiniteTimeWindow => new FiniteTimeWindow(min(this.start,x.start),max(this.end, x.end))
		case x: MinusInfTimeWindow => new MinusInfTimeWindow(max(this.end,x.end))
		case x: PlusInfTimeWindow => new PlusInfTimeWindow(min(this.start,x.start))
		case _                 => other
	}

	def -(other: TimeWindow) = other match {
		case x: FiniteTimeWindow => new FiniteTimeWindow(min(max(this.start,x.end),this.end), max(min(this.end,x.start),this.start))
		case x: MinusInfTimeWindow => new MinusInfTimeWindow(min(this.end,x.end))
		case x: PlusInfTimeWindow => new PlusInfTimeWindow(max(this.start,x.start))
		case _                 => -other
	}


	def +(other: FiniteTimeWindow):TimeWindow = (min(start, other.start), max(end, other.end))
	def -(other: FiniteTimeWindow): TimeWindow = (max(this.start, other.start), min(this.end, other.end))
	def plus(other: FiniteTimeWindow):TimeWindow = this + other
	def minus(other: FiniteTimeWindow):TimeWindow = this - other
	//	def min(other: FiniteTimeWindow) = if (this < other) this else other
	//	def max(other: FiniteTimeWindow) = if (this > other) this else other
	

	def unary_- :TimeWindow = TimeWindow(end,start)


	def duration(): Long = end.getTime - start.getTime
//	override def equals(other: Any) = other match {
//		case x: FiniteTimeWindow => start == x.start
//		case _                 => super.equals(other)
//	}
}