import sys

input = sys.stdin.readline


if __name__ == '__main__':
    N, K = map(int, input().split())
    S = list(map(int, input().split()))

    end, cnt = 0, 0
    ans = 0
    cnt += S[end]%2
    for start in range(N):
        while end<N-1 and cnt+S[end+1]%2 <= K:  # *조건에 만족할때 까지 end포인터 이동
            end+=1
            cnt+=S[end]%2
        ans = max(ans, end-start+1-cnt)         # 조건 끝(K개 다 채움)이면 이때의 최대 길이 구하기
        cnt -= S[start]%2   # start에 대한 for문 코드, start 앞으로 이동

    print(ans)

# while end<N-1 and cnt+S[end+1]%2 <= K: cnt세는것과 end가 이동한 위치를 같게하기위해 end+1로 이용(cnt++되면 end가 앞으로 감으로)
# end가 예상보다 한 칸 더 앞서가지 않기위해 end+1로 먼저 판별하고 괜찮으면 이동

# *초기 코드*
# while right<len(S):
#     while cnt<K and right<len(S):
#         if S[right]%2!=0:
#             cnt+=1
#         right+=1                # right는 K개 빼서 만든 수열의 끝보다 하나 더 뒤에를 가르킴
#
#     ans = max(ans, right-left-cnt)      # 배열은 0부터 시작함으로 현재 배열보다 하나 뒤인 right-left-cnt가 알맞음
#
#     while cnt>=K:
#         if S[left]%2!=0:
#             cnt-=1
#         left+=1
# print(ans)

# >> cnt가 K와 같거나 클때의 값 지정이 잘못됨 > 무한루프 발생