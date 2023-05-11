import sys

input = sys.stdin.readline


def dfs(start, remain, learn):
    global mx

    # 배울 알파벳 모두 선택 시, 배울 수 있는 단어 카운트
    if remain == 0:
        cnt = 0
        for i in range(n):
            if (words[i] & learn) == words[i]:
                cnt += 1
        mx = max(mx, cnt)
        return

    # 배울 단어 선택하기
    for j in range(start, 26):
        if learn & (1 << j) == 0:  # 현재 알파벳(j)를 아직 배우지 않았다면
            learn |= (1 << j)  # j를 배움
            dfs(j, remain - 1, learn)  # 또 다른 단어 배우러 감
            learn &= ~(1 << j)  # 배운 j 다시 안배운걸로 원상복구


if __name__ == '__main__':
    n, k = map(int, input().split())
    words = []
    learn = 0
    mx = 0
    for _ in range(n):
        word = input().strip()
        num = 0
        for i in range(len(word)):  # 입력 단어들을 비트로 입력받음 (해당하는 알파벳 == 1)
            num |= 1 << (ord(word[i]) - ord('a'))
        words.append(num)

    if k < 5 or k == 26:
        print(n if k == 26 else 0)
    else:
        learn |= 1 << (ord('a') - ord('a'))
        learn |= 1 << (ord('n') - ord('a'))
        learn |= 1 << (ord('t') - ord('a'))
        learn |= 1 << (ord('i') - ord('a'))
        learn |= 1 << (ord('c') - ord('a'))
        dfs(0, k - 5, learn)
        print(mx)

# 1 << i : i번째 비트만 1
# & : 모두 1일때만 1, 원하는 두 비트 a,b가 둘 다 1이여야지 같은 것
