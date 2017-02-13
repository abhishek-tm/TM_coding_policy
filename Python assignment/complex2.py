""" Create a class to perform addition and subtraction of two complex number"""
class ComplexNumber(object):
    
    """Representation of the complex number. define variable 
    and bind with class instance using self"""
    def __init__(self, real_number=0, imaginar_number=0):
        self.real_part = real_number
        self.imaginary_part = imaginar_number

    """Calculate sum of two complex number"""
    def __add__(self, other):
        #create a result instance of complexNumber class  
        result = ComplexNumber()
        result.real_part = self.real_part + other.real_part
        result.imaginary_part = self.imaginary_part + other.imaginary_part
        #return result of addtion opration
        return result
    
    """calculate subtraction of two number"""
    def __sub__(self, other):
        #create a result instance of complexNumber class  
        result = ComplexNumber()
        result.real_part = self.real_part - other.real_part
        result.imaginary_part = self.imaginary_part - other.imaginary_part
        #return result of subtract method
        return result
    
    """__str__() method used to string representation of object"""
    def __str__(self):
        if self.imaginary_part >= 0:
            result = "{0:.2f}+{1:.2f}i".format(self.real_part, self.imaginary_part)
        else:
            result = "{0:.2f}{1:.2f}i".format(self.real_part, self.imaginary_part)
        return result

#define main method and in which we create instance and read user input and call methods
if __name__ == '__main__':
    # read user input and split function splits a single string
    real_part, imaginar_number = map(float, raw_input().split())
    real_part1, imaginar_number1 = map(float, raw_input().split())
    """object_one and object_two are objects of complexNumber class """
    z1 = ComplexNumber(real_part, imaginar_number)
    z2 = ComplexNumber(real_part1, imaginar_number1)
    print z1+z2
    print z1-z2
