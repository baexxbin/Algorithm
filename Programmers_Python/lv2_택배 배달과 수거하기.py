
# 택배 배달하고, 재활용 상자 수거 (원하는 만큼 가능)
# 트럭은 물류창고 출발 - 배달 및 수거 - 물류창고 되돌아옴
# 트럭의 최소 이동 거리 구하기

def solution(cap, n, deliveries, pickups):
    ans = 0

    d = 0
    p = 0
    for i in range(n-1, -1, -1):    # 먼 곳(i)부터 진행
        cnt = 0
        d -= deliveries[i]          # 이전값 남아있음, 양수라면 이전집을 가면서 커버한거
        p -= pickups[i]

        while d < 0 or p < 0:   # 먼 곳(i)에 여전히 배달과 수거가 존재하면 들려야함
            d+=cap
            p+=cap
            cnt+=1
        ans += (i+1)*2*cnt      # 왕복거리*들린 횟수
    return ans


    # 역순 누적합 버전
    # answer = 0
    # for i in range(n-2, -1, -1):
    #     deliveries[i] += deliveries[i+1]
    #     pickups[i] += pickups[i+1]
    # k = 0
    # for i in range(n-1, -1, -1):
    #     while deliveries[i] > cap*k or pickups[i] > cap*k:
    #         answer += (i+1)*2
    #         k += 1
    # return answer



if __name__ == '__main__':
    cap = 4
    n = 5
    deliveries = [1, 0, 3, 1, 2]
    pickups = [0, 3, 0, 0, 0]
    print(solution(cap, n, deliveries, pickups))