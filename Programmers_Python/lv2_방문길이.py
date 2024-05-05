def solution(dirs):
    dxy = {
        'U': (1, 0),
        'D': (-1, 0),
        'L': (0, -1),
        'R': (0, 1)
    }
    y, x, visited = 5, 5, set()

    for d in dirs:
        dy, dx = dxy[d]
        ny, nx = y+dy, x+dx
        if not (0 <= ny <= 10 and 0 <= nx <= 10):
            continue
        visited.add((y,x,ny,nx))
        visited.add((ny,nx,y,x))
        y, x = ny, nx
    return len(visited)//2


if __name__ == '__main__':
    dirs = "LULLLLLLU"
    print(solution(dirs))


# 처음엔 무작정 bfs로 풀려고했는데 bfs가 아니였음
# 어떤 점을 단순히 True,False처리하는 것도 어떤 방향에서 오느냐에 따라 다르기때문에 그럼 안됨
# 시작,끝 방향을 그래프 문제 풀때처럼 (시작, 끝), (끝, 시작) 두번씩 set에 넣어주어 처음간 길 카운트