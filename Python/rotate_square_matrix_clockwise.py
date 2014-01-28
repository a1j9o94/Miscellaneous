def rotate_clockwise(matrix = []):
    rotation = [[None for i in range(len(matrix))] for j in range(len(matrix[0]))]
    for i in range(len(matrix)):
        index = len(matrix)-1
        for j in range(len(matrix[i])):
            rotation[i][j] = matrix[index-j][i]
    return rotation
    
print(rotate_clockwise([[1,2,3],
                        [4,5,6],
                        [7,8,9]]))