# n개 단위로 잘랐을때, 가장 많이 압축되는 경우 구하기
# 완전 탐색? 1000*1000 = 백만
# n의 범위: 1 ~ s길이의 반만큼
def solution(s):

    ans = float('inf')
    if len(s)==1:
        return 1

    for i in range(1, len(s)//2+1):     # 길이가 1일경우 (1,1)범위여서 for문 실행안됨
        st = 0
        tmp = ''
        cnt = 1

        while st <=len(s):
            cur = s[st:st+i]
            nxt = s[st+i:st+2*i]
            if cur==nxt:
                cnt+=1
                st+=i
                continue
            if cnt==1:
                tmp += cur
            else:
                tmp += str(cnt)+cur
                cnt=1
            st+=i
        ans = min(ans, len(tmp))
    print(ans)


s = "a"
solution(s)
