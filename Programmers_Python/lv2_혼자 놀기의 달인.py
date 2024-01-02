# 어떤 그룹을 먼저 택할 것인지 (3번째 그룹은 점수 카운트 안됨)
def solution(cards):
    global cnt
    ans = [0]
    visited = [False] * (len(cards) + 1)
    for idx in range(1, len(cards) + 1):
        cnt = 1
        if not visited[idx]:
            visited[idx] = True
            dfs(cards, idx, visited)
            ans.append(cnt)
    ans.sort(reverse=True)
    return ans[0] * ans[1]


def dfs(cards, idx, visited):
    global cnt
    if not visited[cards[idx - 1]]:
        visited[cards[idx - 1]] = True
        cnt += 1
        dfs(cards, cards[idx - 1], visited)


cards = [8, 6, 3, 7, 2, 5, 1, 4]
print(solution(cards))
