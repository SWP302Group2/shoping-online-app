
window.onload = function () {
    let bars = document.getElementsByClassName('bars')[0];
    let left = document.getElementsByClassName('left')[0];
    let welcomeUser = document.getElementsByClassName('right__welcome-user')[0];
    let subnav = document.getElementsByClassName('right__subnav')[0];
    let subMenuList = [left, subnav];
    let mediaList = ['max-width:750px'];

    if (window.matchMedia(mediaList[0])) {
        if (bars) {
            bars.addEventListener('keydown', processKeyboardEventForLeft);
        }
    }
    if (welcomeUser) {
        welcomeUser.addEventListener("keydown", processKeyboardEventForSubnav);
    }
    document.addEventListener('click', processClickEvent);

    function processKeyboardEventForLeft(event) {
        let needProcess = processKeyboard(event);
        if (needProcess) {
            processShowTarget(left);
        }
    }

    function processKeyboardEventForSubnav(event) {
        let needProcess = processKeyboard(event);
        if (needProcess) {
            processShowTarget(subnav);
        }
    }

    function processKeyboard(event) {
        let needProcess = false;
        if (event.code === 'Enter') {
            needProcess = true;
        }
        if (event.code === 'Space') {
            needProcess = true;
        }
        return needProcess;
    }

    function processShowTarget(target) {
        let isVisible = isVisibleTarget(target);
        if (isVisible) {
            displayNone(target);
        }
        if (!isVisible) {
            closeAllSubMenu();
            displayFlex(target);
        }
    }

    function isVisibleTarget(target) {
        if (target == undefined) {
            return false;
        }
        if (window.getComputedStyle(target, null).display === 'none') {
            return false;
        }
        if (window.getComputedStyle(target, null).visibility === 'hidden') {
            return false;
        }
        if (window.getComputedStyle(target, null).opacity == 0) {
            return false;
        }
        return true;
    }

    function displayNone(target) {
        if (target) {
            target.style.display = null;
        }
    }

    function displayFlex(target) {
        if (target) {
            target.style.display = 'flex';
        }
    }

    function processClickEvent(event) {
        let isProcessedEvent = false;
        let target = event.target;
        if (!isProcessedEvent && welcomeUser.contains(target)) {
            processShowTarget(subnav);
            isProcessedEvent = true;
        }
        if (!isProcessedEvent && bars.contains(target)) {
            processShowTarget(left);
            isProcessedEvent = true;
        }
        if (!isProcessedEvent && subnav.contains(target)) {
            isProcessedEvent = true;
        }
        if (!isProcessedEvent && left.contains(target)) {
            isProcessedEvent = true;
        }
        if (!isProcessedEvent) {
            closeAllSubMenu();
        }
    }

    function closeAllSubMenu() {
        for (target of subMenuList) {
            displayNone(target);
        }
    }

}