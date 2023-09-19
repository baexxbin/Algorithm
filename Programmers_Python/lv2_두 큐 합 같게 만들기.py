from collections import deque


def solution(queue1, queue2):
    sum1 = sum(queue1)
    sum2 = sum(queue2)

    cnt = 0
    limit = len(queue1) * 3

    que1 = deque(queue1)
    que2 = deque(queue2)

    if (sum1 + sum2) % 2 != 0:
        return -1

    while cnt <= limit:
        if sum1 == sum2:
            return cnt
        if sum1 > sum2:
            tmp = que1.popleft()
            que2.append(tmp)
            sum1 -= tmp
            sum2 += tmp
        else:
            tmp = que2.popleft()
            que1.append(tmp)
            sum2 -= tmp
            sum1 += tmp
        cnt += 1

    return -1

# limit = len(queue1) * 3 >> 큐1, 큐2를 한바퀴 돌린값(len(que)*2) + (한바퀴 돌았을때도 불가능한 예외로 인해+1) >> *3해주기