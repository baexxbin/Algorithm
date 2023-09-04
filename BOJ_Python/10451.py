import sys

input = sys.stdin.readline


def findCycle(idx):
    visited[idx] = True
    nxt = premutation[idx]
    if not visited[nxt]:
        findCycle(nxt)


if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        N = int(input())
        premutation = [0] + list(map(int, input().split()))
        visited = [False]*(N+1)

        adj = [0]*(N+1)
        for i, v in enumerate(premutation):
            adj[i] = v

        ans = 0
        for idx in range(1, N+1):
            if not visited[idx]:
                findCycle(idx)
                ans+=1
        print(ans)