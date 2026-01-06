def reverse_array(arr):
    offset_counter = 1
    for i in range(int(len(arr)/2)):
        cached_val = arr[i]
        arr[i] = arr[len(arr) - offset_counter]
        arr[len(arr) - offset_counter] = cached_val
        offset_counter+=1
    return arr

test = [5, 4, 3, 2, 1]
print(reverse_array(test))