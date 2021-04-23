//package zio.sql.mysql.avinder
//
//import zio.blocking.Blocking
//import zio.sql.mysql.{ MysqlModule, MysqlRunnableSpec }
//import zio.sql.{ ConnectionPoolConfig, JdbcRunnableSpec, TestContainer }
//import zio.test._
//import zio.test.environment.TestEnvironment
//import zio.{ Has, ZIO, ZLayer }
//
//import java.util.Properties
//import scala.language.postfixOps
//
//object AvinderMySqlSpec
//    extends /* DefaultRunnableSpec with JdbcRunnableSpec with MysqlModule */ MysqlRunnableSpec
//    with InvoiceSchema {
//
//  import Invoices._
//
//  final case class Invoice(id: Int, paymentStatusId: Int)
//
////  private def connProperties(user: String, password: String): Properties = {
////    val props = new Properties
////    props.setProperty("user", user)
////    props.setProperty("password", password)
////    props
////  }
////
////  val poolConfigLayer: ZLayer[Blocking, Throwable, Has[ConnectionPoolConfig]] = TestContainer
////    .mysql()
////    .map(a => Has(ConnectionPoolConfig(a.get.jdbcUrl, connProperties(a.get.username, a.get.password))))
////
////  override def spec: Spec[TestEnvironment, TestFailure[Any], TestSuccess] =
////    specLayered.provideCustomLayerShared(jdbcLayer)
//
//  def specLayered = suite("Avi's dodgy MySql suite")(testM("test1") {
//    /*
//    val query: MysqlModuleTest.Read.Select[((MysqlModuleTest.Features.Source :||: MysqlModuleTest.Features.Source) :||: MysqlModuleTest.Features.Source) :||: MysqlModuleTest.Features.Source, (UUID, Cons.this.tail.ResultTypeRepr), Nothing] = select(customerId ++ fName ++ lName ++ dob) from customers
//
//     */
//    val query = select(id ++ paymentStatusId) from invoices
//
//    val testResult = execute(query.to[Int, Int] { case row =>
//      Invoice(row._1, row._2)
//    })
//
//    for {
//      _ <- ZIO.unit
//    } yield assertCompletes
//  })
//}
