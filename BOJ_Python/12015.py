import sys

input = sys.stdin.readline

def binarySearch(e):
    left = 0
    right = len(LIS) - 1

    while left <= right:
        mid = (left + right) // 2
        if LIS[mid] == e:
            return mid
        if LIS[mid] < e:
            left = mid + 1
        else:
            right = mid - 1
    return left


if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, input().split()))
    LIS = [arr[0]]      # 해당 길이의 마지막 수열 값 저장

    for i in range(N):
        if arr[i] > LIS[-1]:
            LIS.append(arr[i])
        else:
            idx = binarySearch(arr[i])
            LIS[idx] = arr[i]

    print(len(LIS))