# 9:46 - 11-12

def solution(plans):
    # 시작 시간 순 정렬 (문자열 -> int)
    for i, cur in enumerate(plans):
        h, m = map(int, cur[1].split(':'))
        cur[1] = (h * 60) + m
        plans[i][1] = cur[1]
        plans[i][2] = int(cur[2])

    plans.sort(key=lambda x: x[1])

    stack = []
    ans = []
    for i in range(len(plans) - 1):
        stack.append([plans[i][0], plans[i][2]])
        gap = plans[i + 1][1] - plans[i][1]
        while stack and gap:
            if stack[-1][1] <= gap:
                cn, ct = stack.pop()
                gap -= ct
                ans.append(cn)
            else:
                stack[-1][1] -= gap
                gap = 0
    ans.append(plans[-1][0])
    while stack:
        ans.append(stack.pop()[0])
    return ans

    # ans = []
    # que = deque()
    # for i in range(1, len(plans)):
    #     now = plans[i - 1]
    #     nxt = plans[i]
    #     print(now, nxt)
    #
    #     finish = now[1] + now[2]
    #     if finish == nxt[1]:  # 다음 과제전까지 현재 과제 딱 맞게 끝냄
    #         ans.append(now[0])
    #         print(f'ans1: {ans}')
    #         continue
    #     elif finish < nxt[1] and que:  # 현재 과제 끝냈는데 다음과제까지 자투리 시간 남음
    #         ans.append(now[0])
    #         print(f'ans2-1: {ans}')
    #         sp_tm = nxt[1] - finish
    #         rest = que.pop()
    #         rest[1] -= sp_tm
    #         print(f'rest[0]: {rest[0]}')
    #         ans.append(rest[0]) if rest[1] == 0 else que.append(rest)
    #         print(f'ans2: {ans}')
    #     else:  # 현재 과제 다 못끝냈는데 다음 과제 해야함
    #         now[2] -= nxt[1] - now[1]
    #         que.append([now[0], now[2]])
    #         print(f'que: {que}')
    #
    # ans.append(plans[-1][0])
    # print(f'ans3: {ans}')
    # while que:
    #     ans.append(que.pop()[0])
    #     print(f'ans4: {ans[0]}')
    #
    # print(ans)


plans = [["science", "12:40", "50"], ["music", "12:20", "40"], ["history", "14:00", "30"], ["computer", "12:30", "100"]]
solution(plans)
