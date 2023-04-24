import sys

input = sys.stdin.readline

# 비트마스킹 사용
if __name__ == '__main__':
    n, m = map(int, input().split())
    k, *lst = map(int, input().split())
    partyList = []
    tmpList = []
    know = 0
    check = [True] * m

    # know 배열 초기화
    for i in lst:
        know |= (1 << i)

    for _ in range(m):
        t, *tmp = map(int, input().split())
        tmpList.append(tmp)

    # 파티 참석자 비트로 표현
    for t in tmpList:
        tmp = 0
        for i in t:
            tmp |= (1 << i)
        partyList.append(tmp)

    # know는 계속 업데이트되므로 파티 수(m)만큼 반복
    for _ in range(m):
        for i, party in enumerate(partyList):
            # tmp에 know 원소가 있으면 know 업데이트 (두 집합의 교집합이 공집합이 아닐때, tmp에 없던 party원소 추가(합집합))
            if party & know != 0:
                know |= party
                check[i] = False

    print(sum(i for i in check if True))
