def reverse(arr):
    offset = 1
    print(len(arr)//2)

    for i in range(len(arr)//2):
        cache = arr[i]
        arr[i] = arr[len(arr)-offset]
        arr[len(arr)-offset] = cache
        offset+=1
    return arr

test = [1, 2, 3, 4, 5]
print(reverse(test))