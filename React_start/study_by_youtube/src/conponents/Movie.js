import React from "react";



const Movie = ( {movie, removeMovie} ) => {
    return (
        <div className={"movie"}>
            <div className={"title"}>{movie.title}
                <span className={"year"}>
                    ({movie.year})
                </span>
            </div>
            <div>
                <button onClick={() => removeMovie(movie.id)}>삭제</button>
            </div>
        </div>
    );
}

export default Movie;