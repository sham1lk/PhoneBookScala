import scala.io.StdIn

@main
def main(): Unit = {
  var exit = false
  val phoneBook: FilePhoneBook = FilePhoneBook("book.txt")
  
  def processChoice(choice: Int): Unit =
    choice match
      case 0 => exit = true
      case 1 => phoneBook.addRecord()
      case 2 => phoneBook.findPhoneByName()
      case 3 => phoneBook.findNameByPhone()
      case 4 => phoneBook.savePhoneBook()
      case 5 => phoneBook.showPhoneBook()
      case _ => println("Некорректный выбор, попробуйте еще раз.")

  while !exit do
    println("Выберите операцию:")
    println("0 - выйти")
    println("1 - добавить запись (имя и телефон)")
    println("2 - найти телефон по имени")
    println("3 - найти имя по телефону")
    println("4 - сохранить текущие данные в файл")
    println("5 - показать содержимое всей телефонной книги")

    val choice = StdIn.readInt()
    processChoice(choice)
}