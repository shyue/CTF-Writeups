import sys
import hashlib
import base64
import struct

BUF_SIZE = 65536 

stringList = [];

for i in range(0, 9*8*7*6*5*4*3):
    digits = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    n = 7;
    choices = 9;
    compartment = 9*8*7*6*5*4*3;
    result = 0;
    holdI = i;

    while (n>0):
        compartment = compartment/choices
        box = holdI/(compartment)
        result = 10*result+digits[box]
        holdI-=box*(compartment)
        choices-=1
        n-=1
        digits[box] = digits[choices]
        
    stringList.append(result)



for i in stringList:
    
    fh = open("AndroidManifest.xml", "r+b")
    
    fh.seek(2072,0)
    fh.write(struct.pack("i", i))
    fh.close()

    sha1 = hashlib.sha1()
    with open("AndroidManifest.xml", 'rb') as f:
        while True:
            data = f.read(BUF_SIZE)
            if not data:
                break
            sha1.update(data)
    val2 = base64.b64encode(bytes((sha1.digest())))
    if val2 == "F0T3vG9oImHgTmMPeAu0dfJ0sVk=":
        print i
        break
    
