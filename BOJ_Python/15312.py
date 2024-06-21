import sys

input = sys.stdin.readline

if __name__ == '__main__':
    A = input().strip()
    B = input().strip()

    alpha = [3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1]
    lst = []
    for i in range(len(A)):
        lst.append((alpha[ord(A[i])-65]))
        lst.append((alpha[ord(B[i])-65]))

    while len(lst) != 2:
        tmp = []
        for i in range(len(lst)-1):
            tmp.append((lst[i]+lst[i+1])%10)
        lst = tmp
    print(''.join(map(str, lst)))