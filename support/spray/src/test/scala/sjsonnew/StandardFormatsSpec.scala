/*
 * Copyright (C) 2011 Mathias Doenitz
 * Adapted and extended in 2016 by Eugene Yokota
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sjsonnew

import support.spray.Converter
import spray.json.{ JsValue, JsNumber, JsString, JsNull, JsTrue, JsFalse }
import org.specs2.mutable._
import scala.Right

class StandardFormatsSpec extends Specification with BasicJsonProtocol {

  "The optionFormat" should {
    "convert None to JsNull" in {
      Converter.toJsonUnsafe(None.asInstanceOf[Option[Int]]) mustEqual JsNull
    }
    "convert JsNull to None" in {
      Converter.fromJsonUnsafe[Option[Int]](JsNull) mustEqual None
    }
    "convert Some(Hello) to JsString(Hello)" in {
      Converter.toJsonUnsafe(Some("Hello").asInstanceOf[Option[String]]) mustEqual JsString("Hello")
    }
    "convert JsString(Hello) to Some(Hello)" in {
      Converter.fromJsonUnsafe[Option[String]](JsString("Hello")) mustEqual Some("Hello")
    }
  }

  "The eitherFormat" should {
    val a: Either[Int, String] = Left(42)
    val b: Either[Int, String] = Right("Hello")

    "convert the left side of an Either value to Json" in {
      Converter.toJsonUnsafe(a) mustEqual JsNumber(42)
    }
    "convert the right side of an Either value to Json" in {
      Converter.toJsonUnsafe(b) mustEqual JsString("Hello")
    }
    "convert the left side of an Either value from Json" in {
      Converter.fromJsonUnsafe[Either[Int, String]](JsNumber(42)) mustEqual Left(42)
    }
    "convert the right side of an Either value from Json" in {
      Converter.fromJsonUnsafe[Either[Int, String]](JsString("Hello")) mustEqual Right("Hello")
    }
  }

  // "The tuple1Format" should {
  //   "convert (42) to a JsNumber" in {
  //     Tuple1(42).toJson mustEqual JsNumber(42)
  //   }
  //   "be able to convert a JsNumber to a Tuple1[Int]" in {
  //     JsNumber(42).convertTo[Tuple1[Int]] mustEqual Tuple1(42)
  //   }
  // }
  
  // "The tuple2Format" should {
  //   val json = JsArray(JsNumber(42), JsNumber(4.2))
  //   "convert (42, 4.2) to a JsArray" in {
  //     (42, 4.2).toJson mustEqual json
  //   }
  //   "be able to convert a JsArray to a (Int, Double)]" in {
  //     json.convertTo[(Int, Double)] mustEqual (42, 4.2)
  //   }
  // }

  // "The tuple3Format" should {
  //   val json = JsArray(JsNumber(42), JsNumber(4.2), JsNumber(3))
  //   "convert (42, 4.2, 3) to a JsArray" in {
  //     (42, 4.2, 3).toJson mustEqual json
  //   }
  //   "be able to convert a JsArray to a (Int, Double, Int)]" in {
  //     json.convertTo[(Int, Double, Int)] mustEqual (42, 4.2, 3)
  //   }
  // }
  // "The tuple4Format" should {
  //   val json = JsArray(JsNumber(42), JsNumber(4.2), JsNumber(3), JsNumber(4))
  //   "convert (42, 4.2, 3, 4) to a JsArray" in {
  //     (42, 4.2, 3, 4).toJson mustEqual json
  //   }
  //   "be able to convert a JsArray to a (Int, Double, Int, Int)]" in {
  //     json.convertTo[(Int, Double, Int, Int)] mustEqual (42, 4.2, 3, 4)
  //   }
  // }
  // "The tuple5Format" should {
  //   val json = JsArray(JsNumber(42), JsNumber(4.2), JsNumber(3), JsNumber(4), JsNumber(5))
  //   "convert (42, 4.2, 3, 4, 5) to a JsArray" in {
  //     (42, 4.2, 3, 4, 5).toJson mustEqual json
  //   }
  //   "be able to convert a JsArray to a (Int, Double, Int, Int, Int)]" in {
  //     json.convertTo[(Int, Double, Int, Int, Int)] mustEqual (42, 4.2, 3, 4, 5)
  //   }
  // }
  // "The tuple6Format" should {
  //   val json = JsArray(JsNumber(42), JsNumber(4.2), JsNumber(3), JsNumber(4), JsNumber(5), JsNumber(6))
  //   "convert (42, 4.2, 3, 4, 5, 6) to a JsArray" in {
  //     (42, 4.2, 3, 4, 5, 6).toJson mustEqual json
  //   }
  //   "be able to convert a JsArray to a (Int, Double, Int, Int, Int, Int)]" in {
  //     json.convertTo[(Int, Double, Int, Int, Int, Int)] mustEqual (42, 4.2, 3, 4, 5, 6)
  //   }
  // }
  // "The tuple7Format" should {
  //   val json = JsArray(JsNumber(42), JsNumber(4.2), JsNumber(3), JsNumber(4), JsNumber(5), JsNumber(6), JsNumber(7))
  //   "convert (42, 4.2, 3, 4, 5, 6, 7) to a JsArray" in {
  //     (42, 4.2, 3, 4, 5, 6, 7).toJson mustEqual json
  //   }
  //   "be able to convert a JsArray to a (Int, Double, Int, Int, Int, Int, Int)]" in {
  //     json.convertTo[(Int, Double, Int, Int, Int, Int, Int)] mustEqual (42, 4.2, 3, 4, 5, 6, 7)
  //   }
  // }
}
