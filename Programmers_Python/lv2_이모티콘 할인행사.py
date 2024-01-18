data = [10, 20, 30, 40]
discount = []
ans = [0,0]
def dfs(tmp, depth):
    if depth==len(tmp):
        discount.append(tmp[:])
        return

    for d in data:
        tmp[depth] += d
        dfs(tmp, depth+1)
        tmp[depth] -= d


def solution(users, emoticons):

    dfs([0]*len(emoticons), 0)

    for i in range(len(discount)):
        join, price = 0, [0]*len(users)
        for j in range(len(emoticons)):
            for k in range(len(users)):
                if users[k][0] <= discount[i][j]:
                    price[k] += emoticons[j] * (100-discount[i][j]) / 100

        for u in range(len(users)):
            if price[u] >= users[u][1]:
                join+=1
                price[u] = 0

        if join >= ans[0]:
            if join == ans[0]:
                ans[1] = max(ans[1], sum(price))
            else:
                ans[1] = sum(price)
            ans[0] = join

    return ans


users = [[40, 10000], [25, 10000]]
emoticons = [7000, 9000]

print(solution(users, emoticons))