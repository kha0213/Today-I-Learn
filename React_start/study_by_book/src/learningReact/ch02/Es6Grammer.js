import React from 'react';

const Es6Grammer = () => {
    function directions(...args) {
        var [start, ...remaining] = args
        var [finish, ...stops] = remaining.reverse()

        console.log(`${args.length} 도시를 운행합니다.`)
        console.log(`${start}에서 출발합니다`)
        console.log(`목적지는 ${finish} 입니다.`)
        console.log(`중간에 ${stops.length}군데 들립니다`)

    }

    directions(
        '서울', '수원', '천안', '대전', '대구', '부산'
    );

    const deepPick = (fields, object = {}) => {
        const [first, ...remaining] = fields.split('.')
        return (remaining.length) ?
            deepPick(remaining.join('.'), object[first]) : object[first]
    }
    const dan = {
        type: 'person',
        data: {
            gender: 'male',
            info: {
                id: 22,
                fullname: {
                    first: 'Dan',
                    last: 'Deacon'
                }
            }
        }
    }

    console.log(deepPick('data.info.fullname.first', dan));

    return (
        <div>

        </div>
    );
};

export default Es6Grammer;