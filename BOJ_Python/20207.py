import sys

input = sys.stdin.readline

# 일정이 연속된 그룹들 (큰 사각형의 가로길이)
# 각 그룹에서 제일 일정이 많이 겹친 날의 수 (큰 사각형의 세로 길이)
if __name__ == '__main__':
    n = int(input())
    calendar = [0]*367
    for _ in range(n):
        s, e = map(int, input().split())
        for i in range(s, e+1):
            calendar[i]+=1

    flag = False
    ans = 0
    width, height = 0, 0
    for k in range(367):        # 배열 마지막날(365)보다 하루 많이 설정, (마지막날까지 일정있는 경우 0으로 일정 끝 알려주기위함)
        if calendar[k] == 0:
            ans += width*height
            height, width = 0, 0
            continue
        width+=1
        height = max(height, calendar[k])
    print(ans)