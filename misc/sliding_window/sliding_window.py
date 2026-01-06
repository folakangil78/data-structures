def slide(arr, k):
    if k > len(arr):
        return -1
    
    max_sum = 0
    for i in range(k):
        max_sum+=arr[i]

    current_sum = max_sum
    for i in range(k, len(arr)):
        current_sum = current_sum - arr[i-k] + arr[i]
        if current_sum > max_sum:
            max_sum = current_sum
    return max_sum

test = [1, 2, 3, 28]
print(slide(test, 3))