package zio.sql.mysql.avinder

import zio.sql.Jdbc

trait InvoiceSchema extends Jdbc { self =>
  import self.ColumnSet._

  object Invoices {
    val invoices =
      (int("id") ++ int("PAYMENT_STATUS_ID"))
        .table("invoices")

    val id :*: paymentStatusId :*: _ = invoices.columns
  }
}
