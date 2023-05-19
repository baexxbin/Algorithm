import sys

input = sys.stdin.readline


def recursion(start, arr):
    if not arr:
        return
    mn = min(arr)
    idx = arr.index(mn)         # idx : 부분배열에서 mn의 위치

    answer[start + idx] = mn    # start+idx : 전체단어에서 mn의 위치
    print("".join(answer))

    recursion(start + idx + 1, arr[idx + 1:])
    recursion(start, arr[:idx])


if __name__ == '__main__':
    word = list(input().strip())
    answer = [""] * len(word)
    recursion(0, word)
