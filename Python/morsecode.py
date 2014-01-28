def checkio(data):
    result = ""
    result += getHour(data[0:data.index(":")])
    result += getMinOrSec(data[data.index(":")+1:nthIndex(data,":",2)])
    result += getMinOrSec(data[nthIndex(data,":",2)+1:])
    print(result)
    return result

def getHour(string):
    print(string)
    return string
def getMinOrSec(string):
    print(string)
    return string
    
def nthIndex(string,substring,n):
    if n <= 1:
        return string.index(substring)
    else:
        return string.index(substring,nthIndex(str,substring,n-1)+1)
        
print(checkio("11:22:33"))