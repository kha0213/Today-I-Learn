import React from 'react';

const App = () => {

    const data = [
        {
            name: 'Baked Salmon',
            ingredients: [
                {name: '연어', amount: 500, measurement: '그램'},
                {name: '잣', amount: 1, measurement: '컵'},
                {name: '버터상추', amount: 2, measurement: '컵'},
                {name: '옐로 스쿼시', amount: 1, measurement: '개'},
                {name: '올리브 오일', amount: 0.5, measurement: '컵'},
                {name: '마늘', amount: 3, measurement: '쪽'}
            ],
            steps: [
                '오븐을 350도로 예열한다.',
                '유리 베이킹 그릇에 올리브 오일을 두른다.',
                '연어, 마늘, 잣을 그릇에 담는다.',
                '오븐에서 15분간 익힌다.',
                '옐로 스쿼시를 추가하고 다시 30분간 오븐에서 익힌다.',
                '오븐에서 그릇을 꺼내서 15분간 식힌 다음 상추를 곁들여서 내놓는다'
            ]
        },
        {
            name: '생선 타코',
            ingredients: [
                {name: '흰살생선', amount: 500, measurement: '그램'},
                {name: '치즈', amount: 1, measurement: '컵'},
                {name: '아이스버그 상추', amount: 2, measurement: '컵'},
                {name: '토마토', amount: 2, measurement: '개(큰 것)'},
                {name: '또띠야', amount: 3, measurement: '개'}
            ],
            steps: [
                '생선을 그릴에 익힌다,',
                '또띠야 3장 위에 생선을 얹는다.',
                '또띠야에 얹은 생선 위에 상추, 토마토, 치즈를 얹는다.'
            ]
        }
    ];

    const Recipe = () => {

    }

    
    return (
        <div>

        </div>
    );
};

export default App;