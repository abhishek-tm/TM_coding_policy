""" import Abstrect base class """
import abc
#define abstract and static method used togther 
abstractstaticmethod = abc.abstractmethod
#define calculater class in which we define abstrect method..
class Calculater:

	__metaclass__ = abc.ABCMeta
	#define abstruct method...abstruct method is incomplite method
	@abstractstaticmethod
	def addition():
		pass

	@abstractstaticmethod
	def subtract():
		pass

	@abstractstaticmethod
	def multiplication():
		pass

	@abstractstaticmethod
	def devide():
		pass
'''preform the addition subtraction multiplication and
division opretion into the class''' 
class Airthematic(Calculater):
	#define static method..static method no need self argument
	#this method perform addition opretion and return result  
	@staticmethod
	def addition(first_number,second_number):
		result = first_number + second_number
		return result
	#this method perform subtract opretion and return result  
	@staticmethod
	def subtract(first_number,second_number):
		result = first_number - second_number
		return result
	#this method perform multiplication opretion and return result
	@staticmethod	
	def multiplication(first_number,second_number):
		
		result = first_number * second_number
		return result
	#this method perform division opretion and return result
	@staticmethod
	def devide(first_number,second_number):
		#check second number must not be zero..
		if second_number != 0:
			result = first_number / second_number
			return result
		else:
			print 'second number must not zero'

# create instance of Airthematic class 
opretion = Airthematic()
#main method
if __name__ == '__main__':
	#display airthematic opretion list 
	print '1: addition'
	print '2: subtract'
	print '3: multiplication'
	print '4: division'
	#while loop for check input  number is valid or not 
	while True:
		try:
			choice = int(raw_input("Enter choice"))
			first_number = int(raw_input('Enter first number:'))
			second_number = int(raw_input('Enter the second number:'))
		# when user enter invalid number and value error occur on console  
		except ValueError:
			print 'Enter invalid value'
			continue
		else:
			#break statement break whlie loop..when user enter valid number 
			break
	'''if block perform diffrent diffrent opretion like addition when first if block execute 
	subtraction, multiplication and division perform other block'''  
	if choice == 1:
		print 'addition of two number',opretion.addition(first_number,second_number)
	if choice == 2:
		print 'subtraction of two number',opretion.subtract(first_number,second_number)
	if choice == 3:
		print 'multiplication of two number',opretion.multiplication(first_number,second_number)
	if choice == 4:
		print 'division of two number',opretion.devide(first_number,second_number)
	#when user enter wrong choice 
	if choice <= 0 or choice >= 5:
		print("Enter wrong choice")



