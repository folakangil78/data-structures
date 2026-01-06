def check_anagram(w1, w2):
    if len(w1) == len(w2):
        w1_sorted = ''.join(sorted(w1))
        w2_sorted = ''.join(sorted(w2))
        if w1_sorted == w2_sorted:
            return True
    return False

from collections import Counter
def check_frequency(w1, w2):
    w1_counter = Counter(w1)
    w2_counter = Counter(w2)

    if w1_counter == w2_counter:
        return True
    return False

def recursive_check(w1, w2):
    if len(w1) == len(w2):
        for i in range(len(w1)):

    return False

if __name__ == "__main__":
    # print(check_anagram("debit card", "bad credit"))
    # print(check_anagram("earth", "heart"))
    # print(check_anagram("listen", "silent"))
    # print(check_anagram("francis", "miranda"))

    print(check_frequency("francis", "miranda"))
    print(check_frequency("silent", "listen"))
    print(check_frequency("heart", "earth"))
    print(check_frequency("debit card", "bad credit"))