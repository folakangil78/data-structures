class Node:
    def __init__(self, value, next=None):
        self.value = value
        self.next = next

    def __str__(self):
        result = []
        current = self

        while current:
            result.append(str(current.value))
            current = current.next

        return ' --> '.join(result)
    
L = Node("a", Node("b", Node("c", Node("d"))))
print(L)


