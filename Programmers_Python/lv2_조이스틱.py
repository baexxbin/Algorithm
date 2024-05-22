def solution(name):
    cnt = 0
    N = len(name)
    mn_move = N-1  # 기존(앞으로 쭉) 초기값

    for i, v in enumerate(name):        # 연속된 A가 여러개 있을 수 있으니, 다 돌면서 체크(제일 긴 연속 A)
        # 상하 이동 최소값
        cnt += min(ord(v)-ord('A'), ord('Z')-ord(v)+1)

        nxt = i+1
        while nxt < N and name[nxt] == 'A':
            nxt+=1

        # 기존(앞으로 쭉), 연속A의 왼쪽 시작, 연속A의 오른쪽 시작 중 최소값으로 갱신
        mn_move = min(mn_move, (2*i)+(N-nxt), i+2*(N-nxt))
    cnt += mn_move
    return cnt




if __name__ == '__main__':
    name = "JEROEN"
    print(solution(name))


# 문제 이해
# A로 초기화된 이름을 주어진 알파벳 이름으로 변경 (최소한의 이동으로)
# * A는 안바껴도 되므로 이동하지 않아도됨 >> 연속된 A는 가지 않아도 됨
# >> 최소 이동
    # 상하 이동 (A부터 목표 알파벳, Z(+1)부터 목표 알파벳) >> 두가지 경우로 정해져 있음
    # 좌우 이동 (순방향으로 쭉 이동, A를 만났을때 되돌아 가기)