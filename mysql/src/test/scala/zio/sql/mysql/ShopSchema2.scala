package zio.sql.mysql

import zio.sql.Jdbc

trait ShopSchema2 extends Jdbc { self =>
  import self.ColumnSet._

  object Customers2 {
    val customers2 =
      (uuid("id") ++ string("first_name")).table("customers")

    val customerId2 :*: fName2 :*: _ = customers2.columns
  }
}
