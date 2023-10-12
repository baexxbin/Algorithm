import heapq


def solution(book_time):
    # book_time 시간으로 변환 (단위 통일로 변환)
    books = []
    for book in book_time:
        tmp = []
        for i in range(2):
            h,m = map(int, book[i].split(':'))
            tmp.append(h*60+m)
        books.append(tmp)

    books.sort()
    rooms = []
    heapq.heappush(rooms, books[0][1])              # 첫 예약의 마감시간 넣기

    for cur in range(1, len(books)):
        if books[cur][0] >= rooms[0]:
            heapq.heappop(rooms)                    # cur시간에서 대실끝난 방을 먼저 pop한 후
        heapq.heappush(rooms, books[cur][1]+10)     # 새로운 cur의 종료시간 넣어주기

    return len(rooms)


book_time = [["15:00", "17:00"], ["16:40", "18:20"], ["14:20", "15:20"], ["14:10", "19:20"], ["18:20", "21:20"]]
solution(book_time)