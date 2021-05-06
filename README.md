# Kotlin, Mvvm Pattern Study<br>
<br>

1. 기본 형태로 구성<br>
  - ViewModel에서 Context를 가지지 않기 위해 최대한 분리<br>
   
   <br>
2. (Android Jetpack) Paging RecyclerView <br>
  ![paging](https://user-images.githubusercontent.com/76588577/117244138-786fd880-ae73-11eb-87c7-19730907b26f.PNG)<br>
  - [출처]  https://www.youtube.com/watch?v=QVMqCRs0BNA<br><br>
  a. paging <br>
    - PagedList : 페이지에서 데이터를 비동기적으로 로드하는 class<br>
    - DataSource : 데이터의 snapshots을 PagedList에 로드해주는 class<br>
    - DataSource.Factory : DataSource 생성 class<br>
    - LivePagedListBuilder : DataSource.Factory + PagedList.Config = LiveData<br>
    - BoundaryCallback : PagedList가 사용 가능한 데이터의 끝에 도달했을 때의 Callback<br>
    - PagedListAdapter - RecyclerView 페이징된 데이터를 표시하는 RecyclerView.Adapter <br>
  <br>
  b. dataSource<br>
    - ItemKeyedDataSource	:로드 된 콘텐츠가 이전에로드 된 항목을 향후로드에 대한 입력으로 사용하는 키가 지정된 콘텐츠를 페이징<br>
    - PageKeyedDataSource	: 요청이 다음 / 이전 페이지에 대한 키를 반환하는 페이지 키 콘텐츠 기준으로 페이징<br>
    - PositionalDataSource : 임의의 페이지 위치에서 고정 크기로드를 지원하는 고정 크기, 계산 가능한 데이터 세트 기준으로 위치를 기반으로 페이징<br>
    <br>
    해당 프로젝트는 PageKeyedDataSource로 사용함<br>
    <br>
  c. Sample<br>
    - Paging을 활용할수 있게끔, step별로 나눠 놓음<br>
    - [참고 사이트] https://developer.android.com/codelabs/android-paging#0
