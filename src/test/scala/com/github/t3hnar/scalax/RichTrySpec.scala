package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

import scala.util.{Failure, Success, Try}

class RichTrySpec extends Specification {

  "RichTry.toEither" should {
    "return Right on Success" in {
      val success: Try[Int] = Success(1)
      success.toEither must beRight(1)
    }

    "return Left on Failure" in {
      val e = new RuntimeException
      val failure: Try[Int] = Failure(e)
      failure.toEither must beLeft(e)
    }
  }

  "RichTry.fold" should {
    "return value on Success" in {
      val success: Try[Int] = Success(1)
      success.fold(
        _ => "",
        i => s"Success $i") mustEqual "Success 1"
    }

    "return error on Failure" in {
      val e = new RuntimeException("Error")
      val failure: Try[Int] = Failure(e)
      failure.fold(
        e => e.getMessage,
        _ => "") mustEqual "Error"
    }
  }
}
