package zio.sql.mysql

import zio.Cause
import zio.sql.mysql.avinder.InvoiceSchema
import zio.test.Assertion._
import zio.test._

import java.time.LocalDate
import java.util.UUID

object MysqlModule2Test extends MysqlRunnableSpec with ShopSchema with InvoiceSchema with ShopSchema2 {

  import Customers2._
  import Customers._

  override def specLayered = suite("Mysql module")(
    testM("Can select from single table") {
      case class Customer(id: UUID, fname: String, lname: String, dateOfBirth: LocalDate)
      case class Customer2(id: UUID, fname: String)
      case class Invoice(id: Int, paymentStatusId: Int)

      val query  = select(customerId ++ fName ++ lName ++ dob) from customers
      val query2 = select(customerId2 ++ fName2) from customers2

      println(renderRead(query))
      println(renderRead(query2))

      val expected =
        Seq(
          Customer(
            UUID.fromString("60b01fc9-c902-4468-8d49-3c0f989def37"),
            "Ronald",
            "Russell",
            LocalDate.parse("1983-01-05")
          ),
          Customer(
            UUID.fromString("f76c9ace-be07-4bf3-bd4c-4a9c62882e64"),
            "Terrence",
            "Noel",
            LocalDate.parse("1999-11-02")
          ),
          Customer(
            UUID.fromString("784426a5-b90a-4759-afbb-571b7a0ba35e"),
            "Mila",
            "Paterso",
            LocalDate.parse("1990-11-16")
          ),
          Customer(
            UUID.fromString("df8215a2-d5fd-4c6c-9984-801a1b3a2a0b"),
            "Alana",
            "Murray",
            LocalDate.parse("1995-11-12")
          ),
          Customer(
            UUID.fromString("636ae137-5b1a-4c8c-b11f-c47c624d9cdc"),
            "Jose",
            "Wiggins",
            LocalDate.parse("1987-03-23")
          )
        )

      val testResult  = execute(
        query
          .to[UUID, String, String, LocalDate, Customer] { case row =>
            Customer(row._1, row._2, row._3, row._4)
          }
      )
      val testResult2 = execute(
        query2
          .to[UUID, String, Customer2] { case row =>
            Customer2(row._1, row._2)
          }
      )
      println(testResult2)

      val assertion = for {
        r <- testResult.runCollect
      } yield assert(r)(hasSameElementsDistinct(expected))

      assertion.mapErrorCause(cause => Cause.stackless(cause.untraced))
    }
  )

}
