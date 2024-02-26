import scala.util.Using
import scala.io.StdIn

class FilePhoneBook(fileName: String) extends BasePhoneBook {
  var  phoneBook: Map[String, String] = loadPhoneBook()
  def loadPhoneBook(): Map[String, String] =
    Using(scala.io.Source.fromFile(fileName)) { source =>
      source.getLines().map { line =>
        val Array(name, phone) = line.split(";")
        name -> phone
      }.toMap
    }.getOrElse(Map.empty)

  def savePhoneBook(): Unit =
    def save(): Unit =
      Using(java.io.PrintWriter(fileName)) { writer =>
        phoneBook.foreach { case (name, phone) =>
          writer.println(s"$name;$phone")
        }
      }

    save()
    println("Текущие данные сохранены в файл.")


  def addRecord(): Unit =
    println("Введите имя:")
    val name = StdIn.readLine()

    println("Введите телефон:")
    val phone = StdIn.readLine()
    if ! (isValidPhone(phone) && isValidName(name)) then
      println(s"Номер или имя введены не правильно")
      return

    phoneBook += (name -> phone)
    println(s"Запись для $name с номером $phone добавлена.")

  def findPhoneByName(): Unit =
    println("Введите имя для поиска телефона:")
    val name = StdIn.readLine()

    val closure: String => Unit = phone => println(s"Телефон для $name: $phone")
    phoneBook.get(name).foreach(closure)

  def findNameByPhone(): Unit =
    println("Введите телефон для поиска имени:")
    val phone = StdIn.readLine()

    val result = phoneBook.find { case (_, value) => value == phone }

    result.foreach { case (name, _) =>
      val partiallyAppliedFunction: String => Unit = name => println(s"Имя для телефона $phone: $name")
      partiallyAppliedFunction(name)
    }

  def showPhoneBook(): Unit =
    val displayFunction: (String, String) => Unit = (name, phone) => println(s"$name: $phone")

    println("Телефонная книга:")
    phoneBook.foreach(displayFunction.tupled)
}
