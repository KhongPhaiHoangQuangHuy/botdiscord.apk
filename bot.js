const { Client, GatewayIntentBits } = require('discord.js');

const token = process.env.TOKEN;

const client = new Client({
    intents: [GatewayIntentBits.Guilds, GatewayIntentBits.GuildMessages],
});

client.once('ready', () => {
    console.log('Bot is online!');
});

client.login(token);
