const divRow = document.querySelector('#div-row');
const divContainer = document.querySelector('#div-container');
const btnSearch = document.querySelector('#btn-search');
const inputSearch = document.querySelector('#input-search');

if(divRow, divContainer, btnSearch, inputSearch) {
    addEvents();
}

async function addEvents() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const page = urlParams.get('page');
    const searchText = urlParams.get('search');

    if(searchText) {
        var search = searchText;
    } else {
        var search = null;
    }

    if(page) {
        var pag = page;
    } else {
        var pag = 0;
    }

    try {

        /* GET posts */
        let url;

        if(search == null) {
            url = 'http://localhost:8080/api/post/page/' + pag;
        } else {
            url = 'http://localhost:8080/api/post/by-title/' + search + '/' + pag;
        }
        
        let responseGetPosts = await fetch(url);
        let jsonGetPosts = await responseGetPosts.json(); // lê o corpo da resposta e converte para o formato JSON

        if(search == null) {
            getPosts(jsonGetPosts, null);
        } else {
            getPosts(jsonGetPosts, search);
        }
    } catch (e) {

    }

    /* btn search */
    btnSearch.addEventListener('click', function() {
        btnSearchRequest(inputSearch.value);
    });  
}

async function btnSearchRequest(textSearch) {

    try {

        /* GET posts */
        let url = 'http://localhost:8080/api/post/by-title/' + textSearch + '/0';
        let responseGetPosts = await fetch(url);
        let jsonGetPosts = await responseGetPosts.json(); // lê o corpo da resposta e converte para o formato JSON
        
        divRow.innerHTML = "";
        
        if(document.querySelector('#nav-id')) {
            document.querySelector('#nav-id').remove();
        }

        getPosts(jsonGetPosts, textSearch);
    } catch (e) {
        
    }
}

async function getPosts(jsonGetPosts, textSearch) {

    var pageNumber = jsonGetPosts.pageable.pageNumber;
    var totalPages = jsonGetPosts.totalPages;
    var isLast = jsonGetPosts.last;

    for(var i=0; i<jsonGetPosts.content.length; i++) { // para cada index no JSON, faça:

        // divRow

            var divCol = document.createElement('div');
            divCol.classList.add('col');
            divCol.setAttribute('id', 'div-col-'+[i]);

                var article = document.createElement('article');
                article.classList.add('card');
                article.classList.add('shadow-sm');

                    var img = document.createElement('img');
                    img.classList.add('bd-placeholder-img');
                    img.classList.add('card-img-top');

                        /* GET post images */
                        let urlPostImage = 'http://localhost:8080/api/postImage/by-post-id/' + jsonGetPosts.content[i].id;
                        let responseGetPostImages = await fetch(urlPostImage);
                        let jsonGetPostImages = await responseGetPostImages.json(); // lê o corpo da resposta e converte para o formato JSON

                        let imgUrl = jsonGetPostImages.body[0].urlImage;

                    img.src = imgUrl;

                    img.alt = 'image 1';
                    img.role = 'img';
                    img.ariaLabel = 'Thumbnail';
                    img.style = 'width: 100%';
                    img.style = 'height: 225%';
                    img.setAttribute('preserveAspectRatio', 'xMidYMid slice');
                    img.setAttribute('focusable', 'false');

                    var divCardBody = document.createElement('div');
                    divCardBody.classList.add('card-body');

                        var pCardText = document.createElement('p');
                        pCardText.classList.add('card-text');
                        pCardText.innerText = jsonGetPosts.content[i].title;

                        var divContent = document.createElement('div');
                        divContent.classList.add('d-flex');
                        divContent.classList.add('justify-content-between');
                        divContent.classList.add('align-items-center');

                            var divBtn = document.createElement('div');
                            divBtn.classList.add('btn-group');

                                var btn = document.createElement('button');
                                btn.classList.add('btn');
                                btn.classList.add('btn-sm');
                                btn.classList.add('btn-outline-secondary');
                                btn.type = 'button';
                                btn.innerText = 'View';

                            divBtn.append(btn);

                            var smallDate = document.createElement('small');
                            smallDate.classList.add('text-muted');
                            smallDate.innerText = '03/06/2022';

                        divContent.append(divBtn, smallDate);
                    divCardBody.append(pCardText, divContent);
                article.append(img, divCardBody);
            divCol.append(article);
        divRow.append(divCol);
    }

    // divRow

        var nav = document.createElement('nav');
        nav.classList.add('mx-auto');
        nav.setAttribute('aria-label', 'Page navigation example');
        nav.setAttribute('id', 'nav-id');

            var ul = document.createElement('ul');
            ul.classList.add('pagination');
            ul.classList.add('justify-content-center');
            ul.classList.add('my-auto');

                if(totalPages <= 4) {
                    for(var i=0; i<totalPages; i++) {
                        
                        var li = document.createElement('li');
                        li.classList.add('page-item');

                            var a = document.createElement('a');
                            if(textSearch == null) {
                                a.href = 'http://localhost:5500/?page=' + i;
                            } else {
                                a.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + i;
                            }
                            a.innerText = i;
                            a.classList.add('page-link');

                            if(i == pageNumber) {
                                a.style = 'background-color: darkgray';
                            }

                        li.append(a);
                        
                        ul.append(li);
                    }
                } else {
                    if(pageNumber == 0) {
                        nextPage1 = pageNumber +1;
                        nextPage2 = pageNumber +2;

                        var li0 = document.createElement('li');
                        li0.classList.add('page-item');
                        li0.classList.add('mx-4');

                            var a0 = document.createElement('a');
                            if(textSearch == null) {
                                a0.href = 'http://localhost:5500/?page=' + 0;
                            } else {
                                a0.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + 0;
                            }
                            a0.innerText = 0;
                            a0.classList.add('page-link');
                            a0.style = 'background-color: darkgray';

                        li0.append(a0);

                        ul.append(li0);

                        for(var i=nextPage1; i<=nextPage2 +1; i++) {

                            var liLoop = document.createElement('li');
                            liLoop.classList.add('page-item');

                                var aLoop = document.createElement('a');
                                if(textSearch == null) {
                                    aLoop.href = 'http://localhost:5500/?page=' + i;
                                } else {
                                    aLoop.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + i;
                                }
                                aLoop.innerText = i;
                                aLoop.classList.add('page-link');

                            liLoop.append(aLoop);
                            
                            ul.append(liLoop);
                        }

                        var li4 = document.createElement('li');
                        li4.classList.add('page-item');
                        li4.classList.add('mx-4');

                            var a4 = document.createElement('a');
                            if(textSearch == null) {
                                a4.href = 'http://localhost:5500/?page=' + Number(totalPages -1);
                            } else {
                                a4.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + Number(totalPages -1);
                            }
                            a4.innerText = totalPages-1;
                            a4.classList.add('page-link');

                        li4.append(a4);
                        
                        ul.append(li4);

                    } else if(isLast) {
                        previousPage1 = pageNumber -2;
                        previousPage2 = pageNumber -3;

                        var li0 = document.createElement('li');
                        li0.classList.add('page-item');
                        li0.classList.add('mx-4');

                            var a0 = document.createElement('a');
                            if(textSearch == null) {
                                a0.href = 'http://localhost:5500/?page=' + 0;
                            } else {
                                a0.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + 0;
                            }
                            a0.innerText = 0;
                            a0.classList.add('page-link');

                        li0.append(a0);

                        ul.append(li0);

                        for(var i=previousPage2; i<=previousPage1 +1; i++) {

                            var liLoop = document.createElement('li');
                            liLoop.classList.add('page-item');

                                var aLoop = document.createElement('a');
                                if(textSearch == null) {
                                    aLoop.href = 'http://localhost:5500/?page=' + i;
                                } else {
                                    aLoop.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + i;
                                }
                                aLoop.innerText = i;
                                aLoop.classList.add('page-link');

                            liLoop.append(aLoop);
                            
                            ul.append(liLoop);
                        }

                        var li4 = document.createElement('li');
                        li4.classList.add('page-item');
                        li4.classList.add('mx-4');

                            var a4 = document.createElement('a');
                            if(textSearch == null) {
                                a4.href = 'http://localhost:5500/?page=' + Number(totalPages -1);
                            } else {
                                a4.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + Number(totalPages -1);
                            }
                            a4.innerText = totalPages -1;
                            a4.classList.add('page-link');
                            a4.style = 'background-color: darkgray';

                        li4.append(a4);
                        
                        ul.append(li4);

                    } else {
                        previousPage = pageNumber -1;
                        nextPage = pageNumber +1;

                        var li0 = document.createElement('li');
                        li0.classList.add('page-item');
                        li0.classList.add('mx-4');

                            var a0 = document.createElement('a');
                            if(textSearch == null) {
                                a0.href = 'http://localhost:5500/?page=' + 0;
                            } else {
                                a0.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + 0;
                            }
                            a0.innerText = 0;
                            a0.classList.add('page-link');

                        li0.append(a0);

                        ul.append(li0);

                        for(var i=previousPage; i<=nextPage; i++) {

                            var liLoop = document.createElement('li');
                            liLoop.classList.add('page-item');

                                var aLoop = document.createElement('a');
                                if(textSearch == null) {
                                    aLoop.href = 'http://localhost:5500/?page=' + i;
                                } else {
                                    aLoop.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + i;
                                }
                                aLoop.innerText = i;
                                aLoop.classList.add('page-link');

                                if(i == pageNumber) {
                                    aLoop.style = 'background-color: darkgray';
                                }

                            liLoop.append(aLoop);
                            
                            ul.append(liLoop);
                        }

                        var li4 = document.createElement('li');
                        li4.classList.add('page-item');
                        li4.classList.add('mx-4');

                            var a4 = document.createElement('a');
                            if(textSearch == null) {
                                a4.href = 'http://localhost:5500/?page=' + Number(totalPages -1);
                            } else {
                                a4.href = 'http://localhost:5500/?search=' + textSearch + '&page=' + Number(totalPages -1);
                            }
                            a4.innerText = totalPages -1;
                            a4.classList.add('page-link');

                        li4.append(a4);
                        
                        ul.append(li4);
                    }
                }

        nav.append(ul);

        if(isLast) {
            divContainer.append(nav);
        } else {
            divRow.append(nav);
        }
}