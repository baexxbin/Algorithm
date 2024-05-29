import sys

input = sys.stdin.readline

def dfs(idx, cnt):
    if idx+tl>wl:
        return cnt

    mxCnt = cnt
    for k in range(idx, wl):
        if word[k:k + tl] == target:
            mxCnt = max(mxCnt, dfs(k + tl, cnt + 1))
            break
    return mxCnt


if __name__ == '__main__':
    word = input().strip()
    target = input().strip()

    wl = len(word)
    tl = len(target)
    mx = 0
    for i in range(wl):
        if word[i:i+tl] == target:
            mx = max(mx, dfs(i+tl, 1))
    print(mx)