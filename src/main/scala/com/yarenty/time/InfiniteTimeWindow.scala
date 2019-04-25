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


class InfiniteTimeWindow extends TimeWindow {
	override def toString = "TimeWindow.Undefined"
//	override def equals(other: Any) = false

	override def unary_- : TimeWindow = this
	private def readResolve(): AnyRef = TimeWindow.Infinite

	def +(other: TimeWindow): TimeWindow = other match {
		case x if x eq TimeWindow.Infinite      => TimeWindow.Infinite
		case x: InfiniteTimeWindow if x ne this => TimeWindow.Infinite
		case _                        => this
	}
	def -(other: TimeWindow): TimeWindow = other match {
		case x if x eq TimeWindow.Infinite      => TimeWindow.Infinite
		case x: InfiniteTimeWindow if x eq this => TimeWindow.Infinite
		case _                        => this
	}


	final def isFinite() = false


	final def toCoarsest: TimeWindow = this

	override def duration(): Long = Long.MaxValue
}