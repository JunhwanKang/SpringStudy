<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://uicdn.toast.com/grid/latest/tui-grid.css" /> 
	<script src="https://uicdn.toast.com/grid/latest/tui-grid.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="grid"></div>
	<script>
        // 웹서버에서 GRID 데이터를 가져오는 방법을 설정한다.
		var clsData = {
			api: {
				readData: { url: 'test001.html', method: 'GET' }
			}
		};

        // GRID 를 생성한다.
		var grid = new tui.Grid( {
			el: document.getElementById('grid'),
			columns: [
				{
					header: '이름',
					name: 'name'
				},
				{
					header: '나이',
					name: 'value',
					editor: 'text'
				}
			],
			data: clsData
		} );
	</script>
</body>
</html>
