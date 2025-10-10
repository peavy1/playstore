package com.test.myapplication

import com.test.myapplication.model.SlideItem

object CommonData {

    val tabTitles = listOf("추천", "인기차트", "업무용 앱", "키즈", "카테고리")

    val items = listOf(
        SlideItem(
            slideImage = "https://play-lh.googleusercontent.com/AzvDWq-V8Uep8kqaaSq-ZEFqKn2F-MWBu_gaw0yD7g6TQtaFsW7SxCU_48_SREuX5ZuXXM4_uRo=w648-h364-rw",
            title = "It’s Libra season",
            subTitle = "Strike a balance with these apps"),
        SlideItem(
            slideImage = "https://play-lh.googleusercontent.com/GDRTfGQE0ZGyRGxLiZ2kBqz0lQXV5D89La_D3rVtrNpqrq5vGH9bZ8B78g1Y4O462yBfm2vF5g=w648-h364-rw",
            title = "sweet treats. deliver to you",
            subTitle = "Curb your cravings with these apps"
        ),
        SlideItem(
            slideImage = "https://play-lh.googleusercontent.com/eNcPa0YYBvZFG8SEIdc-fU7cT5cC_GMSF37OddullGSCSWqXPbbt0WO1FoVpLHG1pcH4G9LSMPxu=w648-h364-rw",
            title = "Apps to learn new DIY skills",
            subTitle = "From crafting to digital design"
        )
    )

    val gameSlideData = listOf(
        SlideItem(
            slideImage = "https://play-lh.googleusercontent.com/SBCUh-fhpgy3BkIJFxk0krqusisnyvcIP5J3qvslwr_rJaIQ5niyhHXLCwSUbbWDCnIvXpW4Ztw2D1YQnGIFJw=w648-h364-rw",
            title = "즐거움이 가득 세계 떠 보세요",
            subTitle = ""),
        SlideItem(
            slideImage = "https://play-lh.googleusercontent.com/GDRTfGQE0ZGyRGxLiZ2kBqz0lQXV5D89La_D3rVtrNpqrq5vGH9bZ8B78g1Y4O462yBfm2vF5g=w648-h364-rw",
            title = "sweet treats. deliver to you",
            subTitle = "Curb your cravings with these apps"
        ),
        SlideItem(
            slideImage = "https://play-lh.googleusercontent.com/eNcPa0YYBvZFG8SEIdc-fU7cT5cC_GMSF37OddullGSCSWqXPbbt0WO1FoVpLHG1pcH4G9LSMPxu=w648-h364-rw",
            title = "Apps to learn new DIY skills",
            subTitle = "From crafting to digital design"
        )
    )

    val searchGame = listOf(
        "액션", "시뮬레이션", "퍼즐" , "어드벤처", "레이싱", "롤플레잉", "전략", "스포츠", "카드", "보드"
    )

    val searchApp = listOf(
        "엔터테인먼트", "소셜", "생산선" , "커뮤니케이션", "음악/오디오", "사진", "쇼핑", "교육", "예술/디자인", "맞춤 설정", "날씨", "뷰티"
    )

    val bookTabLazyRowData = listOf(
        "오늘 뭐 읽지?" to "이번 주를 뜨겁게 달군 핫한 도서들",
        "새로 나온 도서 콜렉션" to "구글 플레이에 막 상륙한 도서들",
        "실시간 Top 도서" to "지금 독자들이 읽고 있는 화제의 도서",
        "우리가 사랑하는 코믹스" to "",
        "판타지/무협 모음전" to "통쾌한 액션과 서사의 세계",
        "오디오 북 콜렉션" to "소리로 책을 읽는다, 추천 오디오북을 여기서!",
        "책 속 살짝 들여다보기" to "무료 샘플로 미리 읽고 구매하세요!",
        "영어 공부를 합시다!" to "",
        "경제/경영을 읽다" to "시야가 딱 트이는 경제/경영 도서",
    )

}