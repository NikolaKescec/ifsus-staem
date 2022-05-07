import React from "react";

import {
  ActionIcon,
  Avatar,
  Button,
  Group,
  Header,
  Menu,
  Text,
  useMantineColorScheme,
} from "@mantine/core";
import { IconLogout, IconMoonStars, IconSun, IconUser } from "@tabler/icons";

import { Link, useNavigate } from "react-router-dom";

import { useAuth0 } from "@auth0/auth0-react";

import LoginButton from "./LoginButton";

export default function MyNavbar() {
  return (
    <Header height={75} p="md">
      <Group position="apart">
        <Group spacing="sm">
          <NavbarLinks />
        </Group>
        <Group spacing="sm">
          <ThemeToggle />
          <LoginButton />
          <UserProfile />
        </Group>
      </Group>
    </Header>
  );
}

function ThemeToggle() {
  const { colorScheme, toggleColorScheme } = useMantineColorScheme();
  const dark = colorScheme === "dark";

  return (
    <ActionIcon
      size="lg"
      variant="outline"
      color={dark ? "yellow" : "blue"}
      onClick={() => toggleColorScheme()}
      title="Toggle color scheme"
    >
      {dark ? <IconSun size={20} /> : <IconMoonStars size={20} />}
    </ActionIcon>
  );
}

function NavbarLinks() {
  const { isAuthenticated } = useAuth0();

  return (
    <>
      <Text component={Link} to="/" color="blue">
        Home
      </Text>
      {isAuthenticated && (
        <>
          <Text component={Link} to="/my-games" color="blue">
            My Games
          </Text>
        </>
      )}
    </>
  );
}

function UserProfile() {
  const { isAuthenticated, user, logout } = useAuth0();
  const navigate = useNavigate();

  if (!isAuthenticated) {
    return null;
  }

  return (
    <Menu
      control={
        <Button px={5}>
          <Avatar src={user?.picture} radius="xl" mr={5} size={30} />{" "}
          {user?.name}
        </Button>
      }
    >
      <Menu.Item icon={<IconUser />} onClick={() => navigate("/profile")}>
        Profile
      </Menu.Item>
      <Menu.Item
        icon={<IconLogout />}
        onClick={() => logout({ returnTo: window.location.origin })}
      >
        Log Out
      </Menu.Item>
    </Menu>
  );
}