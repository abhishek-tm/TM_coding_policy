""" 
define a Queue class. In which we define user define method they perform insertion, deletion opration, and  
check list is empty or not and display list element and size of list etc..
"""
class List:
	# init method automatically called when you create a new instance of the class
	def __init__(self,length = 10,listt = []):
		self.listt = listt
		self.length = length

	""" add method used to append the element into the end of the list 
	self is class argumen. Which have a reference to the current instance of the class.
	element argument used to take a user input and append the end of list"""  
	def add(self,element):
		return self.listt.append(element)
	#delete method perform delete opretion on the queue 
	#queue perform FIFO opretion first come first out
	#delete method remove 0th index element because it's inserted first 
	def delete(self):
		#try block check element are avaliable or not into the queue list 
		#when list is empty the index error occer 
		try:
			return self.listt.pop(0)
		#index error when list is empty 
		except IndexError:
			print 'queue is empty'
	# is empty method check queue list is empty or not and is return Boolean value
	def isEmpty(self):
		return self.listt == []


	def insert(self,index,element):
		#self.index = index
		#self.element = element
		self.length = len(self.listt)
		if length -1 > index: 
			return self.listt.insert(index,element)

	def sort(self):
		print sorted(self.listt)

		#return self.listt.sorted(self.listt)

	def search(self,element):
		return element in self.listt

	#size method return the length of queue list
	def size(self):
		return len(self.listt)

	#display method display the elements list present into the queue 
	def display(self):
		return self.listt

	def get_element(self,index):
		
		return self.listt[index]

if __name__ == '__main__':
	length = int(raw_input('Enter the length of list: '))
	object_of_class = List(length,listt = [])

	#list of opration 
	print '1: add_element'
	print '2: delete_element'
	print '3: isEmpty'
	print '4: insert_element'
	print '5: sort the list element'
	print '6: search element into list'
	print '7: display size of list'
	print '8: display_element_list'
	print '9: display element according to index'

	while True:
		try:
			#length = int(raw_input('Enter the length of list: '))
			choice = int(raw_input("Enter choice: "))
		# when user enter invalid number and value error occur on console  
		except ValueError:
			print 'Enter invalid value'
			#continue
		
    	#when user enter choice between 1 to 5 then if block execute and perform 
		#diffrent opration like add element, delete and check list is empty or not and 
		#size and display""" 
		#when choice 1 enter then add method call and append element 
		if choice == 1:
			
			element = int(raw_input("Enter number: "))
			object_of_class.add(element)
			continue	
		#when user enter choice == 2 then delete() method call and delete.. 
		if choice ==2:
			print 'deleted element is',object_of_class.delete()
			#continue send the program control to the while loop and skip else block and reamnig condition 
			continue
		#when user enter choice == 3 then execute isEmpty() method and return boolean value..
		#when queue is empty the return True else return false 
		if choice == 3:
			print object_of_class.isEmpty()
			continue
		#display length of list 
		if choice == 4:
			element = int(raw_input('enter element: '))
			index = int(raw_input('enter the index: '))
			print 'element insert into list',object_of_class.insert(index,element)
			continue
		# arrange list in sorted order 
		if choice == 5:
			print 'sorted list is: ',object_of_class.sort()
			continue
		# search element into the list. if element present into the list return True else return false
		if choice == 6:
			element = int(raw_input('Enter element for searching: '))
			print object_of_class.search(element)
			continue
		#print the length of list
		if choice == 7:
			print object_of_class.size()
			continue
		#display list of queue element 
		if choice == 8:
			print "element of list is:",object_of_class.display()
			continue
		#extruct element using index 
		if choice == 9:
			index = int(raw_input('enter index: '))
			print'Extrect element is',object_of_class.get_element(index)
			continue
		#when user enter invalid range 
		if  choice <= 0 or choice >= 5:
			print 'enter invalid choice..try again'
		#this block execute when choice is non integer
		#And break statement send out of while loop
		else:
			break
