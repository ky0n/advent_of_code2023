const fs = require('fs');

let start;
fs.readFile('input.txt', 'utf8', (err, data) => {
    if (err) throw err;
    const lines = data.split('\n');
    for (let i = 0; i < lines.length; i++) {
        const line = lines[i];
        console.log(line)
        for (let j = 0; j < line.length; j++) {
            const char = line[j];
            if (char === 'S') {
                start = [i, j];
                console.log(start)
            }
        }
    }
})