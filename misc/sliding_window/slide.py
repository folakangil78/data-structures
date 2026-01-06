def sliding(arr, window):
    max_sum = 0

    for i in range(window):
        max_sum = max_sum + arr[i]

    current_sum = max_sum

    for i in range(window, len(arr)):
        current_sum = current_sum - arr[i-window] + arr[i]
        if current_sum > max_sum:
            max_sum = current_sum

    return max_sum

testing = [45, 67, 89, 45, 100]
print(sliding(testing, 3))