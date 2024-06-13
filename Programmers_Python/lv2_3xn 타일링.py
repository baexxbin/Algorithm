def solution(n):
    ans = [0, 3, 11]
    idx = n//2

    if n%2!=0:
        return 0
    if idx<3:
        return ans[idx]

    for i in range(3, idx+1):
        ans.append((3*ans[i-1] + sum(ans[1:i-1])*2 + 2) % 1000000007)
    return ans[idx]


if __name__ == '__main__':
    n = 4
    print(solution(n))