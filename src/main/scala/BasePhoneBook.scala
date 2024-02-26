trait BasePhoneBook: // trait
  def isValidPhone(phone: String): Boolean =
    phone.matches("[0-9-+]+") && phone.length > 2

  def isValidName(name: String): Boolean =
    name.length > 1 && name.matches("[A-z А-я]+")
  def loadPhoneBook(): Any

  def savePhoneBook(): Any

  def showPhoneBook(): Unit

  def addRecord(): Unit

  def findPhoneByName(): Unit

  def findNameByPhone(): Unit
